#!/usr/bin/env python3
import RPi.GPIO as GPIO
import time
from Adafruit_DHT import read_retry, DHT11

GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

TRIG = 23
ECHO = 24

GPIO.setup(TRIG,GPIO.OUT)
GPIO.setup(ECHO,GPIO.IN)

SENSOR_NAME = DHT11
SENSOR_PIN = 4
time.sleep(2)

def get_humidity_temperature():
    hum, temp = read_retry(SENSOR_NAME, SENSOR_PIN)
    hum = int(hum)
    temp = int(temp)
    return hum, temp

def get_distance():
    GPIO.output(TRIG, False)
    time.sleep(.75)

    GPIO.output(TRIG, True)
    time.sleep(0.00001)
    GPIO.output(TRIG, False)

    pulse_start = None
    pulse_end = None
    while GPIO.input(ECHO)==0:
        pulse_start = time.time()

    while GPIO.input(ECHO)==1:
        pulse_end = time.time()

    assert pulse_start is not None and pulse_end is not None
    pulse_duration = pulse_end - pulse_start

    distance = pulse_duration * 17150
    if 2 < distance < 400:
        return distance
    else:
        return None

def main():
    while True:
        distance = get_distance()
        hum, temp = get_humidity_temperature()

        print("\n{} {} {}".format(distance, temp, hum), end="\r")
        time.sleep(.75)

if __name__ == '__main__':
    main()