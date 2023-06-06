package com.vvi.todo.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {


	String js = """
				{
		     "results":[
		        {
		           "gender":"female",
		           "name":{
		              "title":"Ms",
		              "first":"Viivi",
		              "last":"Rajala"
		           },
		           "location":{
		              "street":{
		                 "number":5864,
		                 "name":"Esplanadi"
		              },
		              "city":"Rusko",
		              "state":"Uusimaa",
		              "country":"Finland",
		              "postcode":12813,
		              "coordinates":{
		                 "latitude":"-27.9937",
		                 "longitude":"68.4982"
		              },
		              "timezone":{
		                 "offset":"+9:00",
		                 "description":"Tokyo, Seoul, Osaka, Sapporo, Yakutsk"
		              }
		           },
		           "email":"viivi.rajala@example.com",
		           "login":{
		              "uuid":"fcad91ce-078c-4e17-bda5-0b2f971a53ba",
		              "username":"greenbird742",
		              "password":"patriot",
		              "salt":"EHU6hBDZ",
		              "md5":"bf003599cee57485336c69d80ef59786",
		              "sha1":"56ea0ba002f7ee6f70faa5d6cfe19f1369080726",
		              "sha256":"cc0b6a017324341a22ae85a345ee5a117f1dc224a0117d5de2962b1c360e5146"
		           },
		           "dob":{
		              "date":"1952-03-22T11:28:02.220Z",
		              "age":71
		           },
		           "registered":{
		              "date":"2021-06-13T22:41:14.939Z",
		              "age":1
		           },
		           "phone":"05-063-276",
		           "cell":"044-637-13-14",
		           "id":{
		              "name":"HETU",
		              "value":"NaNNA442undefined"
		           },
		           "picture":{
		              "large":"https://randomuser.me/api/portraits/women/59.jpg",
		              "medium":"https://randomuser.me/api/portraits/med/women/59.jpg",
		              "thumbnail":"https://randomuser.me/api/portraits/thumb/women/59.jpg"
		           },
		           "nat":"FI"
		        }
		     ],
		     "info":{
		        "seed":"07099678c243f4c3",
		        "results":1,
		        "page":1,
		        "version":"1.4"
		     }
		  }
		""";
	@Test
	void getUserById() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		JsonNode node = mapper.readTree(js);

		System.out.printf("");

	}
}
