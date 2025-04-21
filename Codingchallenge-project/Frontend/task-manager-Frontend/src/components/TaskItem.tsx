import React from "react";
import { Task } from "../types/Task";

import { updateTaskStatus, deleteTask } from "../API/taskApi";

type task = {
  id: number;
  title: string;
  description: string;
  status: string;
  dueDate:string;
};

interface TaskItemProps {
  task: Task;
  setTasks: React.Dispatch<React.SetStateAction<Task[]>>;
}

const TaskItem: React.FC<TaskItemProps> = ({ task, setTasks }) => {
  const handleStatusChange = async () => {
    const updatedTask = await updateTaskStatus(task.id, "COMPLETED");
    setTasks((prev) => prev.map((t) => (t.id === task.id ? updatedTask : t)));
  };

  const handleDelete = async () => {
    await deleteTask(task.id);
    setTasks((prev) => prev.filter((t) => t.id !== task.id));
  };

  return (
    <div>
      <h3>{task.title}</h3>
      <p>{task.description}</p>
      <p>Status: {task.status}</p>
      <p>Due: {new Date(task.dueDate).toLocaleString()}</p>
      <button onClick={handleStatusChange}>Complete</button>
      <button onClick={handleDelete}>Delete</button>
    </div>
  );
};

export default TaskItem;
