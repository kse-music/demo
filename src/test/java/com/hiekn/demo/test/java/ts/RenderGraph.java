package com.hiekn.demo.test.java.ts;

public class RenderGraph{

    private class D3Draw extends D3{

        @Override
        protected void custom() {
            System.out.println("D3-后续draw");
        }
    }

    private class ZoomChartDraw extends ZoomChart{
        @Override
        protected void custom() {
            System.out.println("ZoomChart-后续draw");
        }
    }

    public void draw(Draw draw,GraphData graphData){
        System.out.println("图数据:"+graphData);
        draw.draw();
    }

    public D3Draw getD3Draw(){
        return new D3Draw();
    }

    public ZoomChartDraw getZoomCharDraw(){
        return new ZoomChartDraw();
    }

    public static void main(String[] args) {
        RenderGraph renderGraph = new RenderGraph();
        renderGraph.draw(renderGraph.getD3Draw(),new GraphData());
    }

}
