import os
import google.generativeai as genai  # type: ignore

# Ensure the environment variable is set correctly
api_key = os.environ.get("GEMINI_API_KEY")
if not api_key:
    raise ValueError("API Key not found. Set GEMINI_API_KEY environment variable.")

# Configure the AI with your API key
genai.configure(api_key=api_key)

# Create the model
generation_config = {
    "temperature": 1,
    "top_p": 0.95,
    "top_k": 40,
    "max_output_tokens": 8192,
    "response_mime_type": "text/plain",
}

model = genai.GenerativeModel(
    model_name="gemini-1.5-pro",
    generation_config=generation_config,
    system_instruction="You are a hotel and summerhouse room reservation guide, dedicated to ensure that your touristic mobile application responds to the theme of sustainable development.",
)

# Start the chat session
chat_session = model.start_chat(
    history=[]  # Optionally, add previous messages here if available
)

# Send a message (replace with your input)
response = chat_session.send_message("How can I help you with your room reservation?")

# Print the response
print(response.text)
