interface IColour {
  id: number;
  name: string;
}

interface IPerson {
  id: number;
  firstName: string;
  lastName: string;
  authorised: boolean;
  enabled: boolean;
  colours: IColour[];
}
