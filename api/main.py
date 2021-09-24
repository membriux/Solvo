from typing import List

from fastapi import Depends, FastAPI, HTTPException
from sqlalchemy.orm import Session

import crud, models, schemas
from database import SessionLocal, engine

models.Base.metadata.create_all(bind=engine)

app = FastAPI()

# Dependency

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

@app.get("/")
def hello():
    return {"Status": "up"}


@app.post("/users/", response_model=schemas.User)
def create_user(user: schemas.UserCreate, db: Session = Depends(get_db)):
    db_user = crud.get_user_by_email(db, email=user.email)
    if db_user:
        raise HTTPException(status_code=400, detail="Email already registered")
    return crud.create_user(db=db, user=user)


@app.get("/users/", response_model=List[schemas.User])
def read_users(skip: int = 0, limit: int = 100, db: Session = Depends(get_db)):
    users = crud.get_users(db, skip=skip, limit=limit)
    return users


@app.get("/users/{user_id}", response_model=schemas.User)
def read_user(user_id: int, db: Session = Depends(get_db)):
    db_user = crud.get_user(db, user_id=user_id)
    if db_user is None:
        raise HTTPException(status_code=404, detail="User not found")
    return db_user


@app.post("/users/{user_id}/problems/", response_model=schemas.Problem)
def create_problem_for_user(
    user_id: int, problem: schemas.ProblemCreate, db: Session = Depends(get_db)
):
    return crud.create_user_problem(db=db, problem=problem, user_id=1)

@app.get("/problems/", response_model=List[schemas.Problem])
def read_problems(skip: int = 0, limit: int = 100, db: Session = Depends(get_db)):
    problems = crud.get_problems(db, skip=skip, limit=limit)
    return problems 

@app.post("/problems/{problem_id}/solutions/", response_model=schemas.Solution)
def create_solution_for_problem(
    user_id: int, problem_id: int, solution: schemas.SolutionCreate, db: Session = Depends(get_db)
):
    return crud.create_problem_solution(db=db, solution=solution, problem_id=problem_id, user_id=user_id)