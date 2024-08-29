import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../pages/AuthContext";

const backendUrl = import.meta.env.VITE_BACKEND_URL || "http://localhost:8080";

type Props = {
  setShowForm: (value: boolean) => void;
};

export const AddNewPetForm = ({ setShowForm }: Props) => {
  const [name, setName] = useState("");
  const [type, setType] = useState("");
  const navigate = useNavigate();
  const [error, setError] = useState<string | null>(null);
  const { login } = useAuth();

  const validateForm = () => {
    if (!name || !type) {
      setError("Name and type are required");
      return false;
    }
    return true;
  };
  const sendToBackend = async (name: string, type: string) => {
    try {
      const response = await fetch(`${backendUrl}/api/pets/add`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ name, type }),
      });
      if (!response.status.toString().startsWith("2")) {
        setError("Invalid name or type");
      } else {
        const asJson = await response.json();
        login(asJson.token);
        navigate("/pets");
      }
    } catch (error) {
      console.error("Error:", error);
      setError("Something is wrong. Please try it again.");
    }
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    validateForm();
    sendToBackend(name, type);
    setShowForm(false);
  };

  return (
    <>
      <form className="formDiv" onSubmit={handleSubmit}>
        <h3 className="rf-title">Add new pet:</h3>
        <label className="label">Name:</label>
        <input
          className="input"
          type="text"
          name="name"
          value={name}
          onChange={(event) => setName(event.target.value)}
        />
        <label className="label">Type:</label>
        <input
          className="input"
          type="text"
          name="type"
          value={type}
          onChange={(event) => setType(event.target.value)}
        />
        <button className="btn" type="submit">
          Add
        </button>
      </form>
    </>
  );
};
