const baseUrl = import.meta.env.VITE_BACKEND_URL;

const defaultHeaders = {
  "Content-Type": "application/json",
};

export type RegisterRequest = {
	email: string;
	password: string;
  };
  
  export type RegisterResponse = {
	id: number;
  };
  
  const register = async (request: RegisterRequest): Promise<RegisterResponse> => {
	const response = await fetch(`${baseUrl}/register`, {
	  method: "POST",
	  headers: defaultHeaders,
	  body: JSON.stringify(request),
	});
  
	if (!response.ok) {
	  throw new Error("Failed to register");
	}
  
		return await response.json();
	
  };



  export {register};
