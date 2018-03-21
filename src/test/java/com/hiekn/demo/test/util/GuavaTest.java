package com.hiekn.demo.test.util;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;
import com.google.common.collect.Table.Cell;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.graph.*;
import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class GuavaTest extends TestBase {
	
	@Test
	public void testMultiSet(){
		//
		String[] words = {"a","b","c","a"};
		Map<String, Integer> counts = new HashMap<String, Integer>();
		Multiset<String> multiSet = HashMultiset.create();
		Multimap<String , Integer> map1 = HashMultimap.create();
		Multimap<String , Integer> map2 = ArrayListMultimap.create();
		for (String word : words) {
		  Integer count = counts.get(word);
		  if (count == null) {count=1;
		    counts.put(word, 1);
		  } else {
		    counts.put(word, count + 1);
		  }
		  multiSet.add(word);
		  map1.put(word, count);
		  map2.put(word, count);
		}
	}
	
	@Test  
	public void testListsTransform() {  
		List<PersonDb> personDbs = Lists.newArrayList(new PersonDb("zhangsan", 20),  
				new PersonDb("lisi", 24), new PersonDb("wangwu", 30));  

		//返回的列表是原有列表的一个转换视图，对原有集合的修改当然会反映到新集合中,transform是单向的，无法向结果列表中添加新元素
		List<PersonVo> personVos = Lists.transform(personDbs,  (personDb) -> personDbToVo(personDb));

		for(PersonDb personDb : personDbs) {  
			personDb.setMsg("hello world!");  
		}  
		//Collections.shuffle(personVos);  
		//personVos = ImmutableList.copyOf(personVos);  
		//        personVos = Lists.newArrayList(personVos);  
		for(PersonVo personVo : personVos) {  
			personVo.setMsg("Merry Christmas!");  
		}  
		//        personVos.add(personDbToVo(new PersonDb("sting", 30)));  
		System.out.println(personVos);  
	} 
	
	public PersonVo personDbToVo(PersonDb personDb) {  
		Preconditions.checkNotNull(personDb, "[PersonDbToVo]personDb为null");  
		PersonVo personVo = new PersonVo();  
		personVo.setName(personDb.getName() + ",from Db");  
		personVo.setAge(personDb.getAge());  
		personVo.setMsg(personDb.getMsg());  
		return personVo;  
	}  

	@Test
	public void testEventBus(){
		final AsyncEventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(10));
		
		eventBus.register(new Object() {

			@Subscribe
			@AllowConcurrentEvents
			public void lister(Integer integer) {
//				System.out.printf("%s from int%n", integer);
				System.out.println(Thread.currentThread().getName()+"="+integer);
				for (int i = 0; i < 10; i++) {
					System.out.println(Thread.currentThread().getName()+"="+integer);
				}
			}

			@Subscribe
			public void lister(Number integer) {
//				System.out.printf("%s from Number%n", integer);
			}

			@Subscribe
			public void lister(Long integer) {
				System.out.printf("%s from long%n", integer);
			}
		});
		eventBus.post(1);
		eventBus.post(2);
//		eventBus.post(1L);
	}
	
	@Test
	public void testGraph(){
		MutableGraph<Integer> graph = GraphBuilder.directed().build();
		graph.addNode(1);
		graph.putEdge(2, 3);  // also adds nodes 2 and 3 if not already present
		Set<Integer> successorsOfTwo = graph.successors(2); // returns {3}
		System.out.println(successorsOfTwo);
		graph.putEdge(3, 4);  // no effect; Graph does not support parallel edges
		System.out.println(graph);
		System.out.println(graph.nodes());
		System.out.println(graph.nodes().contains(1));//图上存在2
		System.out.println(graph.successors(2).contains(3));//图上存在边1->2
	}
	
	@Test
	public void testValueGraph(){
		MutableValueGraph<Integer, Double> weightedGraph = ValueGraphBuilder.directed().build();
		weightedGraph.addNode(1);
		weightedGraph.putEdgeValue(2, 3, 1.5);  // also adds nodes 2 and 3 if not already present
		weightedGraph.putEdgeValue(3, 5, 1.5);  // edge values (like Map values) need not be unique
		weightedGraph.putEdgeValue(2, 3, 2.0);  // updates the value for (2,3) to 2.0
		System.out.println(weightedGraph);
		Set<EndpointPair<Integer>> edges = weightedGraph.edges();
		for (EndpointPair<Integer> endpointPair : edges) {
			System.out.println(weightedGraph.edgeValue(endpointPair.source(), endpointPair.target()));
			System.out.println(weightedGraph.edgeValueOrDefault(endpointPair.source(), 9,1.0));
		}
		MutableValueGraph<String, Integer> graph = ValueGraphBuilder.directed().nodeOrder(ElementOrder.<String>natural()).allowsSelfLoops(true).build();
		graph.putEdgeValue("A", "B", 10);
        graph.putEdgeValue("A", "C", 3);
        graph.putEdgeValue("A", "D", 20);
        graph.putEdgeValue("B", "D", 5);
        graph.putEdgeValue("C", "B", 2);
        graph.putEdgeValue("C", "E", 15);
        graph.putEdgeValue("D", "E", 11);
		DijkstraSolve.dijkstra("A","D", graph);
    }

	@Test
	public void testNetWork(){
		
		MutableNetwork<Integer, String> network = NetworkBuilder.directed().build();
		network.addNode(1);
		network.addEdge(2, 3, "2->3");  // also adds nodes 2 and 3 if not already present

		Set<Integer> successorsOfTwo = network.successors(2);  // returns {3}
		System.out.println(successorsOfTwo);
		Set<String> outEdgesOfTwo = network.outEdges(2);   // returns {"2->3"}
		System.out.println(outEdgesOfTwo);

		network.addEdge(2, 3, "2->3 too");  // throws; Network disallows parallel edges
		                                    // by default
		network.addEdge(2, 3, "2->3");  // no effect; this edge is already present
		                                // and connecting these nodes in this order

		Set<String> inEdgesOfFour = network.inEdges(4); // throws; node not in graph
		System.out.println(inEdgesOfFour);
	}

	@Test
	public void testLoadingCache() throws Exception{
		/*
		 * 　　1. cacheLoader
		　　2. callable callback
		　　通过这两种方法创建的cache，和通常用map来缓存的做法比，不同在于，这两种方法都实现了一种逻辑——从缓存中取key X的值，
		如果该值已经缓存过了，则返回缓存中的值，如果没有缓存过，可以通过某个方法来获取这个值。
		但不同的在于cacheloader的定义比较宽泛，是针对整个cache定义的，可以认为是统一的根据key值load value的方法。
		而callable的方式较为灵活，允许你在get的时候指定。
		 */
		LoadingCache<String,String> cahceBuilder=CacheBuilder.newBuilder()
				.build(new CacheLoader<String, String>(){
					@Override
					public String load(String key) throws Exception {        
						String strProValue="hello "+key+"!";                
						return strProValue;
					}

				});        

		System.out.println("jerry value:"+cahceBuilder.getUnchecked("jerry"));
		System.out.println("jerry value:"+cahceBuilder.get("jerry"));
		System.out.println("peida value:"+cahceBuilder.get("peida"));
		System.out.println("peida value:"+cahceBuilder.get("peida"));
		System.out.println("lisa value:"+cahceBuilder.get("lisa"));
		cahceBuilder.put("harry", "ssdded");
		System.out.println("harry value:"+cahceBuilder.getIfPresent("harry"));
		
		System.out.println("harry value:"+cahceBuilder.get(("harry"),new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "ssdded2";
			}
		}));
		
		Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000).build();  
		String resultVal = cache.get("jerry", new Callable<String>() {  
			public String call() {  
				String strProValue="hello "+"jerry"+"!";                
				return strProValue;
			}  
		});  
		System.out.println("jerry value : " + resultVal);

		resultVal = cache.get("peida", new Callable<String>() {  
			public String call() {  
				String strProValue="hello "+"peida"+"!";                
				return strProValue;
			}  
		});  
		System.out.println("peida value : " + resultVal);  
	}
	
	@Test
	public void tableTest(){
		Table<String,String,Integer> tables=HashBasedTable.create();
	    tables.put("a", "javase", 80);
	    tables.put("b", "javaee", 90);
	    tables.put("c", "javame", 100);
	    tables.put("d", "guava", 70);
	    tables.put("e", "", 60);

	    //得到所有行数据 tables.cellSet()
	    Set<Cell<String,String,Integer>> cells = tables.cellSet();
	    for(Cell<String,String,Integer> temp:cells){
	        System.out.println(temp.getRowKey()+" "+temp.getColumnKey()+" "+temp.getValue());
	    }
	    
	    Set<String> students = tables.rowKeySet();
	    for(String str:students){
	        System.out.print(str+"\t");
	    }
	    
	    Set<String> courses = tables.columnKeySet();
	    for(String str:courses){
	        System.out.print(str+"\t");
	    }
	    
	    Collection<Integer> scores = tables.values();
	    for(Integer in:scores){
	        System.out.print(in+"\t");
	    }
	    
	    for(String str:students){
	        Map<String,Integer> rowMap=tables.row(str);
	        Set<Entry<String,Integer>> setEntry=rowMap.entrySet();
	        for(Entry<String,Integer> entry:setEntry){
	            System.out.println(entry.getKey()+" "+entry.getValue());
	        }
	    }
	    
	    for (String str : courses) {
	        Map<String, Integer> rowMap2 = tables.column(str);
	        Set<Entry<String, Integer>> setEntry2 = rowMap2.entrySet();
	        for (Entry<String, Integer> entry : setEntry2) {
	            System.out.println(entry.getKey() + " " + entry.getValue());
	        }
	    }
	}

}  

class PersonDb {  
	private String name;  
	private int age;  
	private String msg;  
	public PersonDb(String name, int age){  
		this.name = name;  
		this.age = age;  
	}  

	public String getName() {  
		return name;  
	}  

	public void setName(String name) {  
		this.name = name;  
	}  

	public int getAge() {  
		return age;  
	}  

	public void setAge(int age) {  
		this.age = age;  
	}  

	public String getMsg() {  
		return msg;  
	}  

	public void setMsg(String msg) {  
		this.msg = msg;  
	}  
	@Override  
	public String toString() {  
		return MoreObjects.toStringHelper(this)  
				.add("name", name)  
				.add("age", age)  
				.add("msg", msg).toString();  
	}  
}  
class PersonVo {  
	private String name;  
	private int age;  
	private String msg;  

	public String getName() {  
		return name;  
	}  

	public void setName(String name) {  
		this.name = name;  
	}  

	public int getAge() {  
		return age;  
	}  

	public void setAge(int age) {  
		this.age = age;  
	}  

	public String getMsg() {  
		return msg;  
	}  

	public void setMsg(String msg) {  
		this.msg = msg;  
	}  

	@Override  
	public String toString() {  
		return MoreObjects.toStringHelper(this)  
				.add("name", name)  
				.add("age", age)  
				.add("msg", msg).toString();  
	}  
}  