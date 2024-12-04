import os
import google.generativeai as genai

genai.configure(api_key=os.environ["AIzaSyB-HNz_pWT2V9TAjHKwPtmfnqrY8JDEC4w"])

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
    system_instruction="You are a hotel and summerhouse room reservation guide,you are dedicated to ensure that your touristic mobile application responds to the theme of sustainable development",
)

chat_session = model.start_chat(
    history=[
    ]
)

response = chat_session.send_message("INSERT_INPUT_HERE")

print(response.text)