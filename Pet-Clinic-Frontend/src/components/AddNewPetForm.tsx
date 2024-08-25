import { useState } from "react";

export const AddNewPetForm = ({ setShowForm }) => {
  const [name, setName] = useState("");
  const [type, setType] = useState("");

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    console.log("send to backend");
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
