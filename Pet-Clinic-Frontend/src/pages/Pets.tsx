import { useEffect, useState } from "react"
import { AddNewPetForm } from "../components/AddNewPetForm"
import { PetProperties, PetsList } from "../components/PetsList"
import { useAuth } from "./AuthContext"

const backendUrl = import.meta.env.VITE_BACKEND_URL

export const Pets = () => {
  const [renderForm, setRenderForm] = useState(false)
  const [error, setError] = useState<string | null>(null)

  const showForm = () => {
    setRenderForm((currentState) => !currentState)
  }

  const [pets, setPets] = useState<PetProperties[]>([])
  const { token } = useAuth()

  useEffect(() => {
    fetch(`${backendUrl}/api/pets/my-pets`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => {
        return res.json()
      })
      .then((data) => {
        setPets(data)
      })
  }, [])

  return (
    <div>
      {renderForm ? (
        <div>
          <AddNewPetForm
            setRenderForm={setRenderForm}
            setParentError={setError}
            setPets={setPets}
          />
        </div>
      ) : (
        <div>
          <PetsList pets={pets} />
          {error && <div className="error-message">{error}</div>}
          <button type="button" className="btn" onClick={showForm}>
            Add new pet
          </button>
        </div>
      )}
    </div>
  )
}
