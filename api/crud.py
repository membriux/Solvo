from sqlalchemy.orm import Session

import models
import schemas


def get_user(db: Session, user_id: int):
    return db.query(models.User).filter(models.User.id == user_id).first()


def get_user_by_email(db: Session, email: str):
    return db.query(models.User).filter(models.User.email == email).first()


def get_users(db: Session, skip: int = 0, limit: int = 100):
    return db.query(models.User).offset(skip).limit(limit).all()


def create_user(db: Session, user: schemas.UserCreate):
    fake_hashed_password = user.password + "notreallyhashed"
    db_user = models.User(
        email=user.email, hashed_password=fake_hashed_password)
    db.add(db_user)
    db.commit()
    db.refresh(db_user)
    return db_user


def create_user_problem(db: Session, problem: schemas.ProblemCreate, user_id: int):
    db_problem = models.Problem(**problem.dict(), owner_id=user_id)
    db.add(db_problem)
    db.commit()
    db.refresh(db_problem)
    return db_problem


def get_problems(db: Session, skip: int = 0, limit: int = 100):
    return db.query(models.Problem).offset(skip).limit(limit).all()


def create_problem_solution(db: Session, solution: schemas.SolutionCreate, user_id: int, problem_id: int):
    db_solution = models.Solution(
        **solution.dict(), owner_id=user_id, problem_id=problem_id)
    db.add(db_solution)
    db.commit()
    db.refresh(db_solution)
    return db_solution


def get_problem_solutions(db: Session, problem_id: int, skip: int = 0, limit: int = 100):
    return (db.query(models.Solution)
            .filter(models.Solution.problem_id == problem_id)
            .offset(skip).limit(limit).all())
