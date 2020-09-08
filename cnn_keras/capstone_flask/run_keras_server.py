from keras.models import load_model
from keras.models import Sequential
from keras.layers import Dense, Activation
from keras.preprocessing.image import img_to_array
from keras.applications import imagenet_utils
from tensorflow.python.keras.backend import set_session
from PIL import Image
import numpy as np
import flask
import io
import tensorflow as tf
import keras
app = flask.Flask(__name__)
model = None
graph = []
def load_models():
        # load the pre-trained Keras model (here we are using a model
        # pre-trained on ImageNet and provided by Keras, but you can
        global model
        model = load_model('Capstone_vgg16_미세조정_0216.h5')
        global graph
        graph = tf.get_default_graph()

def prepare_image(image, target):
    # 만약 이미지가 RGB가 아니라면, RGB로 변환해줍니다.
    if image.mode != "RGB":
        image = image.convert("RGB")

    # 입력 이미지 사이즈를 재정의하고 사전 처리를 진행합니다.
    image_resized = image.resize(target)
    image = np.asarray(image)[:,:,:3]
    image_resized = np.asarray(image_resized)[:,:,:3]
    image_resized = image_resized.astype(np.float32)
    image_resized = np.expand_dims(image_resized, axis=0)

    # 처리된 이미지를 반환합니다.
    return image_resized

@app.route("/predict", methods=["POST"])
def predict():
        # initialize the data dictionary that will be returned from the
        # view
        data = {"success": False}

        # ensure an image was properly uploaded to our endpoint
        if flask.request.method == "POST":
                if flask.request.files.get("image"):
                        # read the image in PIL format
                        image = flask.request.files["image"].read()
                        #image = open('./nose_5.jpg', "rb").read()
                        image = Image.open(io.BytesIO(image))
                        load_models()
                        # preprocess the image and prepare it for classification
                        image = prepare_image(image, target=(150, 150))
                        print(image)
                        # classify the input image and then initialize the list
                        # of predictions to return to the client
                        with graph.as_default():
                                session = keras.backend.get_session()
                                init = tf.global_variables_initializer()
                                session.run(init)
                                preds = model.predict(image)

                                #results = imagenet_utils.decode_predictions(preds)
                                data["predictions"] = []

                        # loop over the results and add them to the list of
                        # returned predictions
                                #for (imagenetID, label, prob) in results[0]:
                                        #r = {"label": label, "probability": float(prob)}
                                r = {"label": "predict" , "probability":int(np.argmax(preds))}
                                print(preds)
                                data["predictions"].append(r)
                        # indicate that the request was a success
                                data["success"] = True

        # return the data dictionary as a JSON response
        return flask.jsonify(data)
        #return int(preds[0])

if __name__ == "__main__":
        print(("* Loading Keras model and Flask starting server..."
                "please wait until server has fully started"))
        #load_models()
        app.run()
