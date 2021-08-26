package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Receiver {


	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RuntimeService runtimeService;

	@JmsListener(destination = "mailbox", containerFactory = "myFactory")
	public void receiveMessage(SimpleApp simpleApp) {

		Map<String,Object> memberMap=new HashMap<>();
		memberMap.put("id",simpleApp.getId());
		memberMap.put("appName",simpleApp.getAppName());
		memberMap.put("appType",simpleApp.getAppType());
		memberMap.put("firstName",simpleApp.getFirstName());
		memberMap.put("lastName",simpleApp.getLastName());

		runtimeService.startProcessInstanceByMessage("MemberEvent",memberMap);

		System.out.println("Received <" + simpleApp + ">");
	}

}
