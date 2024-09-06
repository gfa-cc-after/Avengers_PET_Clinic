import { useEffect, useState } from "react";

export const PetsList = () => {
  const [pets, setPets] = useState([]);
  useEffect(() => {
    fetch("http://localhost:8080/api/pets/my-pets")
      .then((response) => response.json())
      .then((data) => setPets(data));
  }, []);

  return (
    <>
      <h2>Peeeeeeeeeets everywhere!!!!</h2>
      <h4>loading...</h4>
      {console.log(pets)}
    </>
  );
};
