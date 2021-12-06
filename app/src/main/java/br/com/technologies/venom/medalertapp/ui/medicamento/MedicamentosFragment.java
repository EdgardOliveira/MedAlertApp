package br.com.technologies.venom.medalertapp.ui.medicamento;

import static br.com.technologies.venom.medalertapp.utils.Rotinas.trocarTelaComExtra;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import br.com.technologies.venom.medalertapp.R;
import br.com.technologies.venom.medalertapp.adapters.MedicamentoAdapter;
import br.com.technologies.venom.medalertapp.databinding.FragmentMedicamentosBinding;
import br.com.technologies.venom.medalertapp.models.Medicamento;
import br.com.technologies.venom.medalertapp.models.Receita;
import br.com.technologies.venom.medalertapp.utils.RecyclerItemClickListener;
import static br.com.technologies.venom.medalertapp.utils.Constantes.TAG;

public class MedicamentosFragment extends Fragment  implements SwipeRefreshLayout.OnRefreshListener, RecyclerItemClickListener.OnItemClickListener, View.OnClickListener {

    private View view;
    private FragmentMedicamentosBinding binding;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Receita receita;
    private List<Medicamento> medicamentoList;
    private MedicamentoAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private MedicamentosViewModel medicamentosViewModel;

    public static MedicamentosFragment newInstance() {
        return new MedicamentosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        medicamentosViewModel = new ViewModelProvider(this).get(MedicamentosViewModel.class);

        binding = FragmentMedicamentosBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        configurarActionBar();

        //associando objetos da tela à variáveis

        recyclerView = binding.rvMedicamentos;
        swipeRefreshLayout = binding.swpMedicamentos;

        try {
            Log.d(TAG, "onCreateView: passei pra pegar receita");
            receita = (Receita) getArguments().getParcelable("receita");

            Log.d(TAG, "onCreateView: receitas " + receita.getId());
        } catch (Exception e){
            Log.e(TAG, "onCreateView: Ocorreu um erro ao pegar a receita enviada", e);
        }

        configurarSwipeRefresh();

        //configurando escuta de eventos
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, this));

        return view;
    }

    private void configurarActionBar() {
        String titulo = "Medicamentos";
        String subtitulo = "Prescritos para o tratamento";
        Drawable icone = getResources().getDrawable(R.drawable.ic_menu_camera);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);              // Ligando a seta que volta para Activity pai
        actionBar.setTitle(titulo);                             // Configurar o título
        actionBar.setSubtitle(subtitulo);                       // Configurar o sub-título
        actionBar.setDisplayShowHomeEnabled(true);              // Configurar a Home
        actionBar.setLogo(icone);                               // Configurar o ícone que será exibido
        actionBar.setDisplayUseLogoEnabled(false);              // Habilitar a exibição do ícone
    }

    private void configurarSwipeRefresh() {

        //cores de transição
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        //Cor do fundo do refresh
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.black);
    }

    private void configurarRecyclerView() {
        adapter = new MedicamentoAdapter();
        layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
    }

    private void consultarMedicamentos(String receitaId) {
        swipeRefreshLayout.setRefreshing(true);

        medicamentosViewModel.recuperarMedicamentos(receitaId).observe(getViewLifecycleOwner(), new Observer<List<Medicamento>>() {

            @Override
            public void onChanged(List<Medicamento> medicamentoList) {
                if (medicamentoList.size()>0){
                    setMedicamentoList(medicamentoList);
                    adapter.submitList(medicamentoList);
                    //já recebemos os dados... pare o efeito shimmer
                    adapter.setShimmer(false);
                    adapter.notifyDataSetChanged();
                }
                swipeRefreshLayout.setRefreshing(false);
                recyclerView.scrollToPosition(0);
            }
        });
    }

    public void setMedicamentoList(List<Medicamento> medicamentoList) {
        this.medicamentoList = medicamentoList;
    }

    //Fragment
    @Override
    public void onStart() {
        super.onStart();
        consultarMedicamentos(receita.getId());
        configurarRecyclerView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //Swipe Refresh Layout
    @Override
    public void onRefresh() {
        consultarMedicamentos(receita.getId());
        configurarRecyclerView();
    }

    //RecyclerItemClickListener
    @Override
    public void onItemClick(View view, int position) {
        Medicamento medicamentoSelecionado = medicamentoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable("medicamento", medicamentoSelecionado);
        trocarTelaComExtra(getParentFragment(), R.id.action_nav_medicamentos_to_nav_medicamento_detalhe, bundle);
    }

    //RecyclerItemClickListener
    @Override
    public void onLongItemClick(View view, int position) {
        //TODO fazer algo especial quando o usuário fizer um click longo
    }

    //RecyclerItemClickListener
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}