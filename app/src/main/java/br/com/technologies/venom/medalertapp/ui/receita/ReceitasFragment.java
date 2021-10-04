package br.com.technologies.venom.medalertapp.ui.receita;

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
import br.com.technologies.venom.medalertapp.adapters.ReceitaAdapter;
import br.com.technologies.venom.medalertapp.databinding.FragmentReceitasBinding;
import br.com.technologies.venom.medalertapp.models.Medicamento;
import br.com.technologies.venom.medalertapp.models.Receita;
import br.com.technologies.venom.medalertapp.utils.RecyclerItemClickListener;
import static br.com.technologies.venom.medalertapp.utils.Constantes.TAG;
public class ReceitasFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, RecyclerItemClickListener.OnItemClickListener, View.OnClickListener {

    private ReceitasViewModel receitasViewModel;
    private View view;
    private FragmentReceitasBinding binding;
    private RecyclerView recyclerView;
    private ReceitaAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Receita> receitaList;
    private RecyclerView.LayoutManager layoutManager;


    public static ReceitasFragment newInstance() {
        return new ReceitasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        receitasViewModel = new ViewModelProvider(this).get(ReceitasViewModel.class);
        binding = FragmentReceitasBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        configurarActionBar();

        //associando objetos da tela à variáveis

        recyclerView = binding.rvReceitas;
        swipeRefreshLayout = binding.swpReceitas;

        configurarSwipeRefresh();

        //configurando escuta de eventos
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, this));

        return view;
    }

    private void configurarActionBar() {
        String titulo = "Receitas";
        String subtitulo = "Receitas médicas prescritas";
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
        adapter = new ReceitaAdapter();
        layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
    }

    private void consultarReceitas() {
        swipeRefreshLayout.setRefreshing(true);

        receitasViewModel.recuperarReceitas().observe(getViewLifecycleOwner(), new Observer<List<Receita>>() {

            @Override
            public void onChanged(List<Receita> receitaList) {
                if (receitaList.size()>0){
                    setReceitaList(receitaList);
                    adapter.submitList(receitaList);
                    //já recebemos os dados... pare o efeito shimmer
                    adapter.setShimmer(false);
                    adapter.notifyDataSetChanged();
                }
                swipeRefreshLayout.setRefreshing(false);
                recyclerView.scrollToPosition(0);
            }
        });
    }

    public void setReceitaList(List<Receita> receitaList) {
        this.receitaList = receitaList;
    }

    //Fragment
    @Override
    public void onStart() {
        super.onStart();
        consultarReceitas();
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
        consultarReceitas();
        configurarRecyclerView();
    }

    //RecyclerItemClickListener
    @Override
    public void onItemClick(View view, int position) {
        Receita receitaSelecionada = receitaList.get(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable("receita", receitaSelecionada);
        trocarTelaComExtra(getParentFragment(), R.id.action_nav_receitas_to_nav_medicamentos, bundle);
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

    }
}