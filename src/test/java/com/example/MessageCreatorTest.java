package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MessageCreatorTest {
    @Test
    @Ignore
    //TODO: Update the test to mock the SQS Client
    public void testCreator() throws Exception {
        Context context = mock(Context.class);
        LambdaLogger logger = mock(LambdaLogger.class);
        when(context.getLogger()).thenReturn(logger);
        MessageCreator messageCreator = new MessageCreator();
        CustomSQSEvent sqsEvent = new CustomSQSEvent();
        sqsEvent.setMessage("Test Message");
        sqsEvent.setMessageCount(1);
        String result = messageCreator.handleRequest(sqsEvent, context);
        verify(logger).log("Message #1 placed on queue.");
        assertEquals("1 message(s) placed on queue [Test Message]", result);
    }
}
                