import { Fragment } from "react/jsx-runtime";

export type PetProperties = {
    id: number;
    name: string;
    type: string;
};

const Pet = ({ id, name, type }: PetProperties) => {
    return <Fragment key={id}>
        <li key={id}>
            {name} {type}
        </li>
    </Fragment >
}

export { Pet }