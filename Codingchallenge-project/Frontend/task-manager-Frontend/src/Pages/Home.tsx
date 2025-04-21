import { useState, useEffect } from "react";
import TaskForm from "../components/TaskForm";
import TaskList from "../components/TaskList";
import { Task } from "../types/Task";  
import { fetchTasks } from "../API/taskApi";

const Home = () => {
  // ✅ Explicitly define state type as `Task[]`
  const [tasks, setTasks] = useState<Task[]>([]);

  useEffect(() => {
    fetchTasks().then((data) => setTasks(data));
  }, []);

  return (
    <div>
      <h1>Task Manager</h1>
      <TaskForm setTasks={setTasks} />
      <TaskList tasks={tasks} setTasks={setTasks} />
    </div>
  );
  
};

export default Home;

