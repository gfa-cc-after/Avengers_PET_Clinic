import { useState } from "react"
import { AddNewPetForm } from "../components/AddNewPetForm"
import { PetsList } from "../components/PetsList"

export const Pets = () => {
  const [renderForm, setRenderForm] = useState(false)
  const [error, setError] = useState<string | null>(null)

  const showForm = () => {
    setRenderForm((currentState) => !currentState)
  }

  return (
    <div>
      {renderForm ? (
        <div>
          <AddNewPetForm
            setRenderForm={setRenderForm}
            setParentError={setError}
          />
        </div>
      ) : (
        <div>
          <PetsList />
          {error && <div className="error-message">{error}</div>}
          <button type="button" className="btn" onClick={showForm}>
            Add new pet
          </button>
        </div>
      )}
    </div>
  )
}
