import { useEffect, useState } from "react"
import { Pet } from "./Pet"
import type { PetProperties } from "./Pet"

const BASE_URL = import.meta.env.VITE_BACKEND_URL

export const PetsList = () => {
  const [pets, setPets] = useState<PetProperties[]>([])

  useEffect(() => {
    const fetchPosts = async () => {
      const response = await fetch(`${BASE_URL}/api/pets/my-pets`)
      const pets = (await response.json()) as PetProperties[]
      setPets(pets)
    }

    fetchPosts()
  })

  return (
    <>
      <ul>
        {pets.map((pet) => {
          return <Pet key={`pet-id-${pet.id}`} {...pet} />
        })}
      </ul>
    </>
  )
}
