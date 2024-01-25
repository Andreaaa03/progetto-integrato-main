export type profilo = {
    id: number;
    email: string;
    numeroTelefono: string;
    username: string;
    sesso: string;
    follower: number;
    eta: number;
    dataIscrizione: string;
    ruolo: string;
};

export type otherUser = {
    seguito: number;
    username: string;
    amico: boolean;
    role: string;
    follower: number;
    following: number;
    stato: string;
};
