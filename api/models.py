from sqlalchemy import Boolean, Column, ForeignKey, Integer, String
from sqlalchemy.orm import relationship

from database import Base

class User(Base):
    __tablename__ = "users"

    id = Column(Integer, primary_key=True, index=True)
    email = Column(String, unique=True, index=True)
    hashed_password = Column(String)
    is_active = Column(Boolean, default=True)

    items = relationship("Item", back_populates="owner")
    problems = relationship("Problem", back_populates="owner")
    solutions = relationship("Solution", back_populates="owner")

class Item(Base):
    __tablename__ = "items"

    id = Column(Integer, primary_key=True, index=True)
    title = Column(String, index=True)
    description = Column(String, index=True)
    owner_id = Column(Integer, ForeignKey("users.id"))

    owner = relationship("User", back_populates="items")

class Problem(Base):
    __tablename__ = "problems"

    id = Column(Integer, primary_key=True, index=True)
    title = Column(String, index=True)
    description = Column(String, index=True)
    owner_id = Column(Integer, ForeignKey("users.id"))
    solutions = relationship("Solution", back_populates="problem")

    owner = relationship("User", back_populates="problems")

    # TODO: createdAt (date)
    # TODO: solutions [solution] – similar to post comments

class Solution(Base):
    __tablename__ = "solutions"

    id = Column(Integer, primary_key=True, index=True)
    description = Column(String, index=True)
    owner_id = Column(Integer, ForeignKey("users.id"))
    problem_id = Column(Integer, ForeignKey("problems.id"))

    owner = relationship("User", back_populates="solutions")
    problem = relationship("Problem", back_populates="solutions")

    # TODO: createdAt (date)
    # TODO: solutions [solution] – similar to post comments
