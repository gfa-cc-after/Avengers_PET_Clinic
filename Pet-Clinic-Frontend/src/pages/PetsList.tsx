import { useEffect, useState } from "react";
import { Pet } from "../components/Pet";

const BASE_URL = import.meta.env.VITE_BACKEND_URL;

type Pet = {
  id: number;
  name: string;
  type: string;
};

export const PetsList = () => {
  const [pets, setPets] = useState<Pet[]>([]);

  useEffect(() => {
    const fetchPosts = async () => {
      const response = await fetch(`${BASE_URL}/api/pets/my-pets`);
      const pets = (await response.json()) as Pet[];
      setPets(pets);
    };

    fetchPosts();
  });

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
