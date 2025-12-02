export interface Favorito {
  id: number;
  idUsuario: number;
  idMedicamento: number;
  medicamentoNome: string;
  principioAtivo: string;
  farmaciaNome: string;
  disponivel: boolean;
  descricao?: string;
  showDetalhes?: boolean;
  categoria?: string;
  quantidadeEstoque?: number;
  data_cadastro: string;
}
