import { useEffect, useState } from "react";

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
      const response = await fetch(`${BASE_URL}/pets`);
      const pets = (await response.json()) as Pet[];
      setPets(pets);
    };

    fetchPosts();
  });

  return (
    <>
      <hr />
      <h2> Pets list</h2>
      <ul>
        {pets.map((pet) => {
          return (
            <li key={pet.id}>
              {pet.name} {pet.type}
            </li>
          );
        })}
      </ul>
    </>
  );
};
