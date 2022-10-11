export interface Transaction {
  id: string;
  userId: string;
  proceedType: string;
  paymentType: string;
  amount: number;
  name: string;
  creatorUsername?: string;
  description?: string;
  createDate?: string;
}
