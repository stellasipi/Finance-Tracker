export interface Transaction {
  id: string;
  userId: string;
  proceedType: string;
  paymentType: string;
  amount: number;
  name: string;
  description?: string;
  createDate?: string;
}
