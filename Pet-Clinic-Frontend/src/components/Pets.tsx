import Pet from "./Pet";

export const Pets = () => {
  const pets = [
    {
      id: 1,
      name: "eddie",
      type: "dog",
    },
    {
      id: 2,
      name: "brownie",
      type: "cat",
    },
    {
      id: 3,
      name: "chark",
      type: "parrot",
    },
    {
      id: 4,
      name: "colly",
      type: "shark",
    },
  ];

  return (
    <div>
      <ul>
        {pets.map((pet) => (
          <Pet key={pet.id} name={pet.name} type={pet.type} />
        ))}
      </ul>
    </div>
  );
};
