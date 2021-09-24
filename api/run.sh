python -m venv venv
source venv/bin/activate
pip install -r requirements.txt
pip install -U pip
uvicorn main:app --host 0.0.0.0 --port 8000 --reload

