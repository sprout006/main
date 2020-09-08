 # python simple_request.py

# import the necessary packages
import requests
import importlib
# initialize the Keras REST API endpoint URL along with the input
# image path
KERAS_REST_API_URL = "http://localhost:5000/predict"
SPRINGBOOT_URL = "http://localhost:8082/result"
IMAGE_PATH = "forehead_s.jpeg"

# load the input image and construct the payload for the request
image = open(IMAGE_PATH, "rb").read()
payload = {"image": image}

# submit the request
r = requests.post(KERAS_REST_API_URL, files=payload).json()

# ensure the request was sucessful
if r["success"]:
        #importlib.reload(request)
        for (i, result) in enumerate(r["predictions"]):
                print("{}. {}: {:.4f}".format(i + 1, result["label"],result["probability"]))
        tmp=(r["predictions"][0]["probability"])
        payload["result"]=tmp
        t = requests.get(SPRINGBOOT_URL,files=payload,verify=False)
        #print(t["success"])

# otherwise, the request failed
else:
        print("Request failed")

