import { useState } from "react";
import { Task } from "../types/Task";

interface TaskFormProps {
  setTasks: React.Dispatch<React.SetStateAction<Task[]>>;
}

const TaskForm: React.FC<TaskFormProps> = ({ setTasks }) => {
  const [title, setTitle] = useState("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const newTask: Task = { id: Date.now(), title, description: "", status: "PENDING", dueDate: new Date().toISOString() };
    setTasks((prevTasks) => [...prevTasks, newTask]);
    setTitle("");
  };

  return (
    <form onSubmit={handleSubmit}>
      <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} placeholder="Enter task title" required />
      <button type="submit">Add Task</button>
    </form>
  );
};

export default TaskForm;
