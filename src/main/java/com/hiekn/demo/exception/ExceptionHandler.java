package com.hiekn.demo.exception;

import com.google.common.collect.Lists;
import com.hiekn.demo.bean.result.ErrorCodes;
import com.hiekn.demo.bean.result.RestResp;
import com.hiekn.demo.config.CommonResource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;

@Configuration
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	@Override
	public Response toResponse(Exception exception) {
		ErrorCodes code = ErrorCodes.SERVICE_ERROR;
		Status statusCode = Status.OK;
        String errMsg = "";

		if(exception instanceof BaseException){
            code = ((BaseException) exception).getCode();
            errMsg = ((BaseException) exception).getMsg();
		}else if(exception instanceof WebApplicationException){
			code = ErrorCodes.HTTP_ERROR;
			if(exception instanceof NotFoundException){
				statusCode = Status.NOT_FOUND;
			}else if(exception instanceof NotAllowedException){
				statusCode = Status.METHOD_NOT_ALLOWED;
			}else if(exception instanceof NotAcceptableException){
				statusCode = Status.NOT_ACCEPTABLE;
			}else if(exception instanceof InternalServerErrorException){
				statusCode = Status.INTERNAL_SERVER_ERROR;
			}
		}

        Integer errorCode = code.getErrorCode();
        errMsg = StringUtils.isBlank(errMsg)?code.getInfo():errMsg;
        exception.setStackTrace(Lists.newArrayList(exception.getStackTrace()).stream().filter(s -> s.getClassName().contains(CommonResource.BASE_PACKAGE)).collect(Collectors.toList()).toArray(new StackTraceElement[]{}));
        logger.error("ErrorMsg = {}",errMsg,exception);
        return Response.ok(new RestResp<>(errorCode,errMsg)).status(statusCode).build();
    }
	
}
