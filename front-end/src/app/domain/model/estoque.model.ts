import { Pharmacy } from "./pharmacy.model";
import { Medication } from "./medication.model";

export interface Estoque {
  id: number;
  medicamentoId: number;
  farmaciaId: number;
  quantidade: number;
  medicamento?: Medication;
  farmacia?: Pharmacy;
}
