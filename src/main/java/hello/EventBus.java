package hello;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.core.variable.CoreVariableInstance;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventBus implements JavaDelegate {
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    ProcessEngine processEngine;
    @Override
    public void execute(DelegateExecution context) throws Exception {
        ExecutionEntity executionCasted = ((ExecutionEntity) context);
        List<CoreVariableInstance> vars = executionCasted.getVariableInstancesLocal();
        Map<String, Object> varsMap = new HashMap<>();
        for (CoreVariableInstance v : vars) {
            varsMap.put(v.getName(), v.getTypedValue(true).getValue());
        }
        String payload = (String) varsMap.get("payload");
        String event = (String) varsMap.get("event");
        String processInstanceId = context.getProcessInstanceId();
        if(event!=null&&event.equalsIgnoreCase("FormatlyCheck")) {
            boolean approved = (Boolean) context.getVariable("approved");
            if (approved == true) {
                     jmsTemplate.convertAndSend("formatlyCheckApproved", new SimpleApp("NewPayment", true, "new Payment", "shehab", "Tarek", processInstanceId,"FormatlyCheckApproved"));




            } else {
                jmsTemplate.convertAndSend("formatlyCheckRejected", new SimpleApp("NewPayment", false, "new Payment", "shehab", "Tarek", processInstanceId,"FormatlyCheckRejected"));
            }
        }

    }
}
