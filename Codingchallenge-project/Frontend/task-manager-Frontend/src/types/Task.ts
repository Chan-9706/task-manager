export interface Task {
    id: number;
    title: string;
    description: string;
    status: "PENDING" | "IN_PROGRESS" | "COMPLETED"; // Match your enum in backend
    dueDate: string; // Use string if it’s coming as an ISO date string
  };
  