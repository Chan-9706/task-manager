import axios from "axios";
import { Task } from "../types/Task";

const API_BASE_URL = "http://localhost:8080/api/tasks";

export const fetchTasks = async (): Promise<Task[]> => {
  const response = await axios.get(API_BASE_URL);
  return response.data;
};

export const createTask = async (task: Task): Promise<Task> => {
  const response = await axios.post(API_BASE_URL, task);
  return response.data;
};

export const deleteTask = async (id: number): Promise<void> => {
  await axios.delete(`${API_BASE_URL}/${id}`);
};

export const updateTaskStatus = async (id: number, status: Task["status"]): Promise<Task> => {
  const response = await axios.patch(`${API_BASE_URL}/${id}/status?status=${status}`);
  return response.data;
};
