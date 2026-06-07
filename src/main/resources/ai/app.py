from flask import Flask, request, jsonify
from sklearn.linear_model import LogisticRegression
import numpy as np

app = Flask(__name__)


X = np.array([
    [95, 90],
    [90, 85],
    [85, 80],
    [75, 70],
    [65, 60],
    [55, 50],
    [45, 40],
    [35, 30],
    [25, 20]
])

y = np.array([
    1,
    1,
    1,
    1,
    1,
    0,
    0,
    0,
    0
])

model = LogisticRegression()

model.fit(X, y)



@app.route('/predict', methods=['POST'])
def predict():

    data = request.json

    attendance = data['attendance']
    exam_score = data['examScore']

    prediction = model.predict([
        [attendance, exam_score]
    ])

    probability = model.predict_proba([
        [attendance, exam_score]
    ])

    result = "GOOD" if prediction[0] == 1 else "WEAK"

    confidence = float(max(probability[0]) * 100)

    return jsonify({
        "prediction": result,
        "confidence": round(confidence, 2)
    })


if __name__ == '__main__':
    app.run(debug=True, port=5000)