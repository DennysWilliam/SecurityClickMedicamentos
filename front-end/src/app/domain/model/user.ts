import { UserRole } from "./user-role";

export interface User {
  id: number;
  nome: string;
  email: string;
  cpf: string;
  telefone: string;
  municipio: string;
  uf: string;
  role: UserRole;
  senha: string;
  endereco: string;
}
