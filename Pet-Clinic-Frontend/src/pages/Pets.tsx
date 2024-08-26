import { AddNewPetForm } from "../components/AddNewPetForm";
import { PetsList } from "../components/PetsList";
import { useState } from "react";

export const Pets = () => {
  const [renderForm, setShowForm] = useState(false);

  const showForm = () => {
    setShowForm(!renderForm);
  };

  return (
    <div>
      {renderForm ? (
        <div>
          <PetsList />
          <AddNewPetForm setShowForm={setShowForm} />
        </div>
      ) : (
        <button className="btn" onClick={showForm}>
          Add new pet
        </button>
      )}
    </div>
  );
};