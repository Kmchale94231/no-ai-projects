from win11toast import toast
import requests
import os
from dotenv import load_dotenv
from pathlib import Path


url = "https://api.tomorrow.io/v4/weather/forecast"

load_dotenv()
api_key = os.getenv("TOMORROW_API_KEY")

parameters = {
    "location": "32.6098,-85.4808",
    "timesteps": ["1d"],
    "units": "imperial",
    "apikey": api_key
}

headers = {
    "accept": "application/json"
}

response = requests.get(url, params=parameters, headers=headers)
data = response.json()

today = data["timelines"]["daily"][0]
date = today["time"]
values = today["values"]

temp_high = values["temperatureMax"]
temp_low = values["temperatureMin"]
uv_max = values["uvIndexMax"]

icon = {
    'src': str(Path('auburn-al-banner.jpg').resolve()),
    'placement': 'appLogoOverride'
}

toast(f"Todays Weather:\n Date: {date}\n Low: {temp_low}\n High: {temp_high}\n Low: {temp_low}\n UV High: {uv_max}", icon=icon)
