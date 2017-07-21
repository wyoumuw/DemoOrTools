package com.youmu.maven.test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.youmu.maven.utils.builder.ListBuilder;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by dehua.lai on 2017/6/21.
 */
public class JsonTest {
	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void test1() throws IOException {
		mapper.configure(MapperFeature.INFER_PROPERTY_MUTATORS, false);
		mapper.setConfig(mapper.getSerializationConfig().with(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
		ObjectNode root = new ObjectNode(mapper.getNodeFactory());
		root.put("name", "妖梦");
		root.put("age", 10);
		ObjectNode louguandao = new ObjectNode(mapper.getNodeFactory());
		louguandao.put("name", "楼观");
		ObjectNode bailoujian = new ObjectNode(mapper.getNodeFactory());
		bailoujian.put("name", "白楼");
		root.putPOJO("weapons", new ListBuilder<ObjectNode>().add(louguandao).add(bailoujian).build());
		root.putPOJO("matsuri", new Date());
		String json = mapper.writeValueAsString(root);
		System.out.println(json);
		//root.put();
		root = (ObjectNode) mapper.readTree(json);
	}

	@Test
	public void test2() throws IOException {
		//mapper=mapper.configure(SerializationFeature.INDENT_OUTPUT,true);
		TestEntity testEntity = new TestEntity();
		testEntity.setId(1L);
		testEntity.setName("妖梦");
		TestEntity testEntity2= new TestEntity();
		testEntity2.setId(1L);
		testEntity2.setName("妖梦");
		testEntity.setSelf(testEntity2);
		System.out.println("over");
//		JsonNode jsonNode=mapper.valueToTree(testEntity);
		//System.out.println(jsonNode);

		mapper.acceptJsonFormatVisitor(Date.class, new JsonFormatVisitorWrapper.Base() {
			private JsonObjectFormatVisitor visitor=new JsonObjectFormatVisitor.Base(){
				@Override
				public void property(BeanProperty writer) throws JsonMappingException {
					System.out.println("JsonTest.property");
				}

				@Override
				public void property(String name, JsonFormatVisitable handler, JavaType propertyTypeHint) throws JsonMappingException {
					System.out.println("JsonTest.property");
				}

				@Override
				public void optionalProperty(BeanProperty writer) throws JsonMappingException {
					System.out.println("JsonTest.optionalProperty");
				}

				@Override
				public void optionalProperty(String name, JsonFormatVisitable handler, JavaType propertyTypeHint) throws JsonMappingException {
					System.out.println("JsonTest.optionalProperty");
				}
			};

			@Override
			public JsonObjectFormatVisitor expectObjectFormat(JavaType type) throws JsonMappingException {
				System.out.println("JsonTest.expectObjectFormat");
				return visitor;
			}

			@Override
			public JsonArrayFormatVisitor expectArrayFormat(JavaType type) throws JsonMappingException {
				System.out.println("JsonTest.expectArrayFormat");
				return null;
			}

			@Override
			public JsonStringFormatVisitor expectStringFormat(JavaType type) throws JsonMappingException {
				System.out.println("JsonTest.expectStringFormat");
				return null;
			}

			@Override
			public JsonNumberFormatVisitor expectNumberFormat(JavaType type) throws JsonMappingException {
				System.out.println("JsonTest.expectNumberFormat");
				return null;
			}

			@Override
			public JsonIntegerFormatVisitor expectIntegerFormat(JavaType type) throws JsonMappingException {
				System.out.println("JsonTest.expectIntegerFormat");
				return null;
			}

			@Override
			public JsonBooleanFormatVisitor expectBooleanFormat(JavaType type) throws JsonMappingException {
				System.out.println("JsonTest.expectBooleanFormat");
				return null;
			}

			@Override
			public JsonNullFormatVisitor expectNullFormat(JavaType type) throws JsonMappingException {
				System.out.println("JsonTest.expectNullFormat");
				return null;
			}

			@Override
			public JsonAnyFormatVisitor expectAnyFormat(JavaType type) throws JsonMappingException {
				System.out.println("JsonTest.expectAnyFormat");
				return null;
			}

			@Override
			public JsonMapFormatVisitor expectMapFormat(JavaType type) throws JsonMappingException {
				System.out.println("JsonTest.expectMapFormat");
				return null;
			}
		});
		String s = mapper.writeValueAsString(testEntity);
		System.out.println(s);
		testEntity = mapper.readValue(s, TestEntity.class);
		System.out.println(testEntity);


	}
	@Test
	public void test3() throws IOException {
		mapper=mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
		TestEntity testEntity = new TestEntity();
		testEntity.setId(1L);
		testEntity.setName("妖梦");
		testEntity.setDate(new Date());
		System.out.println("over");
		JsonNode jsonNode=mapper.valueToTree(testEntity);
//		System.out.println(jsonNode);
		//jsonNode=mapper.valueToTree("123");
//		System.out.println(jsonNode);
		System.out.println(jsonNode.isEmpty(mapper.getSerializerProvider()));
		System.out.println(jsonNode.isNull());
		if(null!=jsonNode&&!jsonNode.isEmpty(mapper.getSerializerProvider())&&!jsonNode.isNull()){
			if(jsonNode.isPojo()||jsonNode.isObject()){
				Iterator<Map.Entry<String, JsonNode>> nodes=jsonNode.fields();
				while(nodes.hasNext()){
					Map.Entry<String, JsonNode> nodeEntry=nodes.next();
					JsonNode node=nodeEntry.getValue();
					System.out.print(nodeEntry.getKey()+":");
//					if(!isEmpty(node,mapper.getSerializerProvider())){
//					}else{
//						System.out.println();
//					}
				}
			}
		}
//		String s = mapper.writeValueAsString(testEntity);
//		System.out.println(s);
//		testEntity = mapper.readValue(s, TestEntity.class);
//		System.out.println(testEntity);
//
//		System.out.println(mapper.writeValueAsString("dasdasd"));

	}


	@Test
	public void test4() throws IOException {
		mapper=mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
		TestEntity testEntity = new TestEntity();
		testEntity.setId(1L);
		testEntity.setName("妖梦");
		testEntity.setDate(new Date());
		System.out.println("over");
		JsonGenerator jg= new TokenBuffer(mapper,false);
		JsonSerializer ser=mapper.getSerializerProvider().findTypedValueSerializer(TestEntity.class,true,null);
		ser.serialize(testEntity,jg,mapper.getSerializerProvider());
		System.out.println();
		System.out.println("end");

	}

	boolean isEmpty(JsonNode node, SerializerProvider provider){
		return null==node||node.isEmpty(provider)||node.isNull();
	}
}

class ProviderP extends DefaultSerializerProvider {

	private static final long serialVersionUID = 1L;

	public ProviderP() { super(); }
	public ProviderP(ProviderP src) { super(src); }

	protected ProviderP(SerializerProvider src, SerializationConfig config,
				   SerializerFactory f) {
		super(src, config, f);
	}

	@Override
	public DefaultSerializerProvider copy()
	{
		if (getClass() != ProviderP.class) {
			return super.copy();
		}
		return new ProviderP(this);
	}

	@Override
	public DefaultSerializerProvider createInstance(SerializationConfig config, SerializerFactory jsf) {
		return new ProviderP(this, config, jsf);
	}
}
