public class ParameterizedLogFormat implements LogFormat {
    
    @Override
    public String format(LogMessage message) {
        StringBuilder formattedMessage = new StringBuilder();
        
        // Add timestamp
        formattedMessage.append("[").append(message.getTimeStamp()).append("] ");
        
        // Add log level
        formattedMessage.append("[").append(message.getLogLevel()).append("] ");
        
        // Add thread name
        formattedMessage.append("[").append(message.getThreadName()).append("] ");
        
        // Add class and method
        formattedMessage.append(message.getClassName()).append(".").append(message.getMethodName()).append(" - ");
        
        // Format the message with parameters (log4j-style {} replacement)
        String finalMessage = replacePlaceholders(message.getMessage(), message.getParams());
        formattedMessage.append(finalMessage);
        
        return formattedMessage.toString();
    }
    
    /**
     * Replaces {} placeholders in the message with actual parameter values
     * Similar to log4j/slf4j style
     */
    private String replacePlaceholders(String message, Object[] params) {
        if (params == null || params.length == 0) {
            return message;
        }
        
        StringBuilder result = new StringBuilder();
        int paramIndex = 0;
        int i = 0;
        
        while (i < message.length()) {
            if (i < message.length() - 1 && message.charAt(i) == '{' && message.charAt(i + 1) == '}') {
                // Found a placeholder
                if (paramIndex < params.length) {
                    result.append(params[paramIndex]);
                    paramIndex++;
                } else {
                    result.append("{}"); // No more params, keep placeholder
                }
                i += 2; // Skip both { and }
            } else {
                result.append(message.charAt(i));
                i++;
            }
        }
        
        return result.toString();
    }
}



