type Pet = {
  id: number;
  name: string;
  type: string;
};

export const PetsList = () => {
  const pets: Pet[] = [
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
      <hr />
      <h2> Pets list</h2>
      <div>
        {pets.map((pet) => (
          <div key={pet.id}>
            <div>
              {pet.name} {pet.type}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};
