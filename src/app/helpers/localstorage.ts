export interface UserProps {
  id: number;
  nome: string;
  email: string;
  telefone: string;
  isAdmin: boolean;
}

export const getStorage = (key: string): any => {
  const value = localStorage.getItem(key);
  if (!value) return false;

  const parse = JSON.parse(value);
  return parse;
};

export const getCurrentUserStoraged = (): UserProps => {
  const user: UserProps = getStorage("user");
  return user;
};
