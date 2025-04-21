import React from "react";
import TaskItem from "./TaskItem";
import { Task } from "../types/Task";


type task = {
  id: number;
  title: string;
  description: string;
  status: string;
  duedate: string;
};

interface TaskListProps {
  tasks: Task[];
  setTasks: React.Dispatch<React.SetStateAction<Task[]>>;
}

const TaskList: React.FC<TaskListProps> = ({ tasks, setTasks }) => {
  return (
    <div>
      {tasks.length > 0 ? (
        tasks.map((task) => <TaskItem key={task.id} task={task} setTasks={setTasks} />)
      ) : (
        <p>No tasks available.</p>
      )}
    </div>
  );
};

export default TaskList;
