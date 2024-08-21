import React from "react";

export const Pet = () => {
  type Pet = {
    id: number;
    name: string;
    type: string;
  };
  return (
    <div key={id}>
      <div>
        {name} {type}
      </div>
    </div>
  );
};
