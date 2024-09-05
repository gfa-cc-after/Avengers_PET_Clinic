import { useEffect, useState } from "react";
import { Pet } from "./Pet";
import { useAuth } from "../pages/AuthContext";

const BASE_URL = import.meta.env.VITE_BACKEND_URL || "http://localhost:8080";

type Pet = {
  id: number;
  name: string;
  type: string;
};

export const PetsList = () => {
  const [pets, setPets] = useState<Pet[]>([]);
  const { token } = useAuth();

  useEffect(() => {
    const fetchPosts = async () => {
      const response = await fetch(`${BASE_URL}/api/pets/my-pets`, {
        headers: { Authorization: "Bearer " + token },
      });
      const pets = (await response.json()) as Pet[];
      setPets(pets);
      console.log(token);
      console.log(pets);
    };

    fetchPosts();
  }, [token, setPets, pets]);

  return (
    <>
      <ul>
        {pets.map((pet) => {
          return <Pet {...pet} />;
        })}
      </ul>
    </>
  );
};
