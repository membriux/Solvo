from typing import List, Optional

from datetime import datetime
from pydantic import BaseModel

class SolutionBase(BaseModel):
    description: str
class SolutionCreate(SolutionBase):
    pass
class Solution(SolutionCreate):
    id: int
    owner_id: int
    problem_id: int
    createdAt: datetime

    class Config:
        orm_mode = True
class ProblemBase(BaseModel):
    title: str 
    description: Optional[str] = None

class ProblemCreate(ProblemBase):
    pass
class Problem(ProblemBase):
    id: int 
    owner_id: int
    createdAt: datetime

    solutions: List[Solution] = []

    class Config:
        orm_mode = True

class UserBase(BaseModel):
    email: str

class UserCreate(UserBase):
    password: str

class User(UserBase):
    id: int
    is_active: bool
    
    problems: List[Problem] = []

    class Config:
        orm_mode = True
