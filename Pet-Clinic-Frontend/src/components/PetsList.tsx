import { useEffect, useState } from "react";
import { Pet } from "./Pet";

const BASE_URL = import.meta.env.VITE_BACKEND_URL;

type Pet = {
  id: number;
  name: string;
  type: string;
};

export const PetsList = () => {
  const [pets, setPets] = useState<Pet[]>([]);

  // useEffect(() => {
  //   const fetchPosts = async () => {
  //     const response = await fetch(`${BASE_URL}/api/pets/my-pets`);
  //     const pets = (await response.json()) as Pet[];
  //     setPets(pets);
  //   };

  //   fetchPosts();
  // }, []);

  useEffect(() => {
    const headers = { Authorization: "Bearer my-token" };
    fetch(`${BASE_URL}/api/pets/my-pets`, { headers })
      .then((response) => response.json())
      .then((data) => setPets(data));
  }, []);

  return (
    <>
      <h2>Peeeeeeeeeets everywhere!!!!</h2>
      <table>
        <thead>
          <tr>
            <th>id</th>
            <th>name</th>
            <th>type</th>
          </tr>
        </thead>
        <tbody>
          {pets.map((pet) => (
            <tr key={pet.id}>
              <th>{pet.id}</th>
              <th>{pet.name}</th>
              <th>{pet.type}</th>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};
