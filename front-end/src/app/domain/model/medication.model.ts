export interface Medication {
  id: number;
  nome: string;
  farmaciaId: number;
  farmaciaNome?: string;
  principioAtivo: string;
  descricao?: string;
  data_cadastro?: string;
  disponivel?: boolean;
  quantidadeEstoque?: number;
  favoritado?: boolean;
  showDetalhes?: boolean;
  categoria?: string;
}
