package com.example;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class MessageCreator implements
        RequestHandler<CustomSQSEvent, String> {

    private String queueUrl = "https://sqs.us-west-2.amazonaws.com/xxxxx/ExampleMessageQueue";

    public String handleRequest(CustomSQSEvent sqsEvent, final Context context) {
        LambdaLogger logger = context.getLogger();
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

        for (int i = 1; i <= sqsEvent.getMessageCount(); i++) {

            SendMessageRequest msgRequest = new SendMessageRequest()
                    .withQueueUrl(queueUrl)
                    .withMessageBody(sqsEvent.getMessage() + " - " + i)
                    .withDelaySeconds(0);
            sqs.sendMessage(msgRequest);

            logger.log("Message #" + i + " placed on queue.");
        }
        return sqsEvent.getMessageCount() + " message(s) placed on queue [" + sqsEvent.getMessage() + "]";
    }
}
