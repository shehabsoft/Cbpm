package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Completed {


	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	ProcessEngine processEngine;

	@JmsListener(destination = "completed", containerFactory = "myFactory")
	public void receiveMessage(SimpleApp simpleApp) {

		Map<String,Object> memberMap=new HashMap<>();
		memberMap.put("id",simpleApp.getId());
		memberMap.put("appName",simpleApp.getAppName());
		memberMap.put("appType",simpleApp.getAppType());
		memberMap.put("firstName",simpleApp.getFirstName());
		memberMap.put("lastName",simpleApp.getLastName());
		MessageCorrelationBuilder messageCorrelation3 = processEngine.getRuntimeService().createMessageCorrelation(simpleApp.getEventName());
		System.out.println("processInstanceId :"+simpleApp.getProcessInstanceId());
		if (simpleApp.getProcessInstanceId() != null) {
			messageCorrelation3.processInstanceId(simpleApp.getProcessInstanceId());
		}

		MessageCorrelationResult messageCorrelationResult3 = messageCorrelation3.correlateWithResult();

		System.out.println("Completed <" + simpleApp + ">");
	}

}
