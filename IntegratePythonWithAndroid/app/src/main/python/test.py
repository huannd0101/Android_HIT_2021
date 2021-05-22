import numpy as np
import cv2
def Face_Detection():
    #Load pre-trained model
    face_cascade = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')

    #Capture video from webcam
    cap = cv2.VideoCapture(0)

    while True:

        #Read the frame
        _, img = cap.read()
        img2 = img.copy()

        #convert to grayscale
        gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

        #face detection
        faces = face_cascade.detectMultiScale(gray, 1.1, 4)

        #Draw the rectangle around each face
        for (x, y, w, h) in faces:
            #Các tham số bao gồm hình ảnh, tọa độ đỉnh trên bên trái của HCN,
            #tọa độ đỉnh thứ 2 nằm đối diện với đỉnh 1,
            #màu (B, G, R), và độ dày của hình chữ nhật
            cv2.rectangle(img, (x, y), (x + w, y + h), (0, 255, 0), 2)

        #Show image
        cv2.imshow('Image test1', img)
        cv2.imshow('Image test2', img2)

        # Stop if escape key is pressed
        # key = cv2.waitKey(30) & 0xff
        if cv2.waitKey(1) & 0xFF == ord('q'): # Nhấn phím q để dừng
            break

    #Waiting for any key pressed and close windows
    cap.release()
    cv2.destroyAllWindows()

Face_Detection()