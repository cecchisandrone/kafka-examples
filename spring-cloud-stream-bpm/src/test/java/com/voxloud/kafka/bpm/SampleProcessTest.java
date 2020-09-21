package com.voxloud.kafka.bpm;

import com.example.order.OrderCreatedValue;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.camunda.bpm.extension.mockito.CamundaMockito.autoMock;

@Deployment(resources = "shipment.bpmn")
public class SampleProcessTest extends AbstractProcessEngineRuleTest {

    @Test
    public void start_and_finish_process() {

        autoMock("shipment.bpmn");

        OrderCreatedValue orderCreatedValue =
                OrderCreatedValue.newBuilder().setRequestId("aaa").setItemId("item").setAmount(19).setOrderId(1).build();

        MessageCorrelationResult messageCorrelationResult = processEngine.getRuntimeService()
                                                                         .createMessageCorrelation(orderCreatedValue.getClass().getSimpleName())
                                                                         .processInstanceBusinessKey(orderCreatedValue.getRequestId().toString())
                                                                         .setVariable("orderCreatedValue", Variables
                                                                                 .objectValue(orderCreatedValue)
                                                                                 .serializationDataFormat(Variables.SerializationDataFormats.JAVA)
                                                                                 .create())
                                                                         .correlateWithResult();

        final ProcessInstance processInstance = messageCorrelationResult.getProcessInstance();

        assertThat(processInstance).isWaitingAt("ServiceTask_14nkj7y");

        execute(job());

        assertThat(processInstance).isWaitingAt("Task_0tkly3a");
    }
}